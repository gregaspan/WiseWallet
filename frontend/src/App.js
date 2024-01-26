import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import './App.css';
import Appbar from './Components/Appbar';
import Kategorija from './Components/Kategorija';
import Uporabnik from './Components/Uporabnik';
import logo from './logo.svg';

function App() {
  return (
    <Router>
      <div className="App">
        <Appbar />
        <Routes>
          <Route path="/kategorija" element={<Kategorija />} />
          <Route path="/uporabnik" element={<Uporabnik />} />
          <Route
            path="/"
            element={
              <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <Link
                  className="App-link"
                  to="https://reactjs.org"
                  target="_blank"
                  rel="noopener noreferrer"
                >
                </Link>
              </header>
            }
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
