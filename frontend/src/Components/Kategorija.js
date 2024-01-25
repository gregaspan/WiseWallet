import React, { useEffect, useState } from 'react';
import TextField from '@material-ui/core/TextField';
import Box from '@material-ui/core/Box';
import { Container, Paper, Button } from '@material-ui/core';




export default function Kategorija() {
  const paperStyle = { padding: '50px 20px', width: 600, margin: '20px auto' };
  const [ime, setIme] = useState(''); // State variable for name
  const [kategorije, setKategorije] = useState([]); // State for storing categories

  const handleClick = (e) => {
    e.preventDefault();
    const kategorija = { ime }; // Only name is required
    console.log(kategorija);
    fetch("http://localhost:8080/kategorija/add", {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(kategorija)
    }).then(() => {
      console.log("New Kategorija added");
    });
  };

  useEffect(() => {
    fetch("http://localhost:8080/kategorija/getAll")
    .then(res => res.json())
    .then((result) => {
      setKategorije(result);
    });
  }, []);

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

      <h1>Kategorije</h1>
      <Paper elevation={3} style={paperStyle}>
        {kategorije.map(kategorija => (
          <Paper elevation={6} style={{ margin: '10px', padding: '15px', textAlign: 'left' }} key={kategorija.id}>
            Id: {kategorija.id}<br/>
            Ime: {kategorija.ime}
          </Paper>
        ))}
      </Paper>
    </Container>
  );
}
