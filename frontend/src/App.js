import './styles/App.css';
import Purchase from './pages/purchase/Purchase';
import Balance from './pages/balance/Balance';
import Home from './pages/home/home'
import Navbar from './components/Navbar';

import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'

function App() {
    return (
        <div className="App">
            <Router>
            <Navbar />
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/purchase" element={<Purchase/>}/>
                    <Route path="/balance" element={<Balance/>}/>
                </Routes>
            </Router>

        </div>
    );
}

export default App;
