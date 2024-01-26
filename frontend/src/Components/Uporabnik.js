import React, { useEffect, useState } from 'react';
import TextField from '@material-ui/core/TextField';
import { Container, Paper, Button } from '@material-ui/core';

export default function Uporabnik() {
  const paperStyle = { padding: '50px 20px', width: 600, margin: '20px auto' };
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [uporabniki, setUporabniki] = useState([]);
  const [loginStatus, setLoginStatus] = useState('');

  const handleClick = (e) => {
    e.preventDefault();
    const uporabnik = { email, username, password };
    console.log(uporabnik);
    fetch("http://localhost:8080/uporabnik/add", {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(uporabnik)
    }).then(() => {
      console.log("New Uporabnik added");
    });
  };

  const handleLogin = () => {
    const foundUser = uporabniki.find(user => user.email === email && user.password === password);
    if (foundUser) {
      setLoginStatus('Login Uspesen!');
    } else {
      setLoginStatus('Napacno uporabnisko ime ali geslo.');
    }
  };

  useEffect(() => {
    fetch("http://localhost:8080/uporabnik/getAll")
    .then(res => res.json())
    .then((result) => {
      setUporabniki(result);
    });
  }, []); // The empty array ensures this effect runs only once when the component mounts

  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
        <h1 style={{ color: 'blue' }}><u>Add Uporabnik</u></h1>

        <form noValidate autoComplete="off">
          <TextField
            id="email"
            label="Email"
            variant="outlined"
            fullWidth
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <TextField
            id="username"
            label="Username"
            variant="outlined"
            fullWidth
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <TextField
            id="password"
            label="Password"
            variant="outlined"
            fullWidth
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <Button variant="contained" color="secondary" onClick={handleClick}>
            Submit
          </Button>
          <Button variant="contained" color="primary" onClick={handleLogin}>
            Login
          </Button>
          <p>{loginStatus}</p>
        </form>
      </Paper>

      <h1>Uporabniki</h1>
      <Paper elevation={3} style={paperStyle}>
        {uporabniki.map(uporabnik => (
          <Paper elevation={6} style={{ margin: '10px', padding: '15px', textAlign: 'left' }} key={uporabnik.id}>
            Email: {uporabnik.email}<br/>
            Username: {uporabnik.username}
          </Paper>
        ))}
      </Paper>
    </Container>
  );
}
