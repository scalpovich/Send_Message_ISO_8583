import './App.css';
import Purchase from './components/Purchase';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'

function App() {
  return (
    <div className="App">
      <Router>
      <Routes>
        
        <Route path="/purchase" element={<Purchase />} />
        
      </Routes>
      </Router>
    </div>
  );
}

export default App;
