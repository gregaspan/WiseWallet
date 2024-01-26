import React, { useEffect, useState } from 'react';
import TextField from '@material-ui/core/TextField';
import { Container, Paper, Button } from '@material-ui/core';

export default function Kategorija() {
  const paperStyle = { padding: '50px 20px', width: 600, margin: '20px auto' };
  const [ime, setIme] = useState('');
  const [newName, setNewName] = useState({});
  const [kategorije, setKategorije] = useState([]);
  const [sortOrder, setSortOrder] = useState('asc');

  const fetchKategorije = () => {
    fetch("http://localhost:8080/kategorija/getAll")
      .then(res => res.json())
      .then((result) => {
        sortKategorije(result);
      });
  };

  const sortKategorije = (kategorijeList) => {
    if (sortOrder === 'asc') {
      kategorijeList.sort((a, b) => a.ime.localeCompare(b.ime));
    } else {
      kategorijeList.sort((a, b) => b.ime.localeCompare(a.ime));
    }
    setKategorije(kategorijeList);
  };

  const toggleSortOrder = () => {
    setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
  };

  const handleClick = (e) => {
    e.preventDefault();
    const kategorija = { ime };
    fetch("http://localhost:8080/kategorija/add", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(kategorija)
    }).then(() => {
      console.log("New Kategorija added");
      fetchKategorije();
    });
  };

  const handleDeleteByName = (ime) => {
    fetch(`http://localhost:8080/kategorija/deleteByName/${ime}`, {
      method: "DELETE"
    }).then(() => {
      console.log(`Kategorija with ime ${ime} deleted`);
      fetchKategorije();
    }).catch(error => console.error('Error:', error));
  };

  const handleUpdateByName = (oldName) => {
    fetch(`http://localhost:8080/kategorija/updateByName/${oldName}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ ime: newName[oldName] || oldName })
    }).then(() => {
      console.log(`Kategorija with name ${oldName} updated`);
      fetchKategorije();
    }).catch(error => console.error('Error:', error));
  };

  useEffect(() => {
    fetchKategorije();
  }, [sortOrder]);

  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
        <h1 style={{ color: 'blue' }}><u>Add Kategorija</u></h1>
        <form noValidate autoComplete="off">
          <TextField
            id="outlined-basic"
            label="Kategorija Ime"
            variant="outlined"
            fullWidth
            value={ime}
            onChange={(e) => setIme(e.target.value)}
          />
          <Button variant="contained" color="secondary" onClick={handleClick}>
            Submit
          </Button>
        </form>
      </Paper>

      <Button variant="contained" onClick={toggleSortOrder}>
        Sort {sortOrder === 'asc' ? 'Z-A' : 'A-Z'}
      </Button>

      <h1>Kategorije</h1>
      <Paper elevation={3} style={paperStyle}>
        {kategorije.map(kategorija => (
          <Paper elevation={6} style={{ margin: '10px', padding: '15px', textAlign: 'left' }} key={kategorija.id}>
            Ime: {kategorija.ime}
            <Button variant="contained" color="secondary" onClick={() => handleDeleteByName(kategorija.ime)}>
              Delete
            </Button>
            <TextField
              label="New Name"
              variant="outlined"
              value={newName[kategorija.ime] || ''}
              onChange={(e) => setNewName({ ...newName, [kategorija.ime]: e.target.value })}
            />
            <Button variant="contained" color="primary" onClick={() => handleUpdateByName(kategorija.ime)}>
              Update Name
            </Button>
          </Paper>
        ))}
      </Paper>
    </Container>
  );
}
