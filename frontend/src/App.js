import './styles/App.css';
import Purchase from './pages/purchase/Purchase';
import Result from './components/Result';
import MainBalance from './pages/balance/Main';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'

function App() {
  return (
    <div className="App">
      <Router>
      <Routes>
        
        <Route path="/purchase" element={<Purchase />} />
        <Route path="/result" element={<Result />} />
        <Route path="/balance" element={<MainBalance/>}/>
      </Routes>
      </Router>

    </div>
  );
}

export default App;
