import './App.css';
import Purchase from './components/Purchase';
import Result from './components/Result';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'

function App() {
  return (
    <div className="App">
      <Router>
      <Routes>
        
        <Route path="/purchase" element={<Purchase />} />
        <Route path="/result" element={<Result />} />

      </Routes>
      </Router>
    </div>
  );
}

export default App;
