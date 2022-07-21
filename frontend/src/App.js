import './styles/App.css';
import Purchase from './pages/purchase/Purchase';
import Balance from './pages/balance/Balance';
import Home from './pages/home/home'
import Navbar from './components/Navbar';
import Withdraw from './pages/withdraw/Withdraw';
import SingleTransaction from './pages/singleTransaction/SingleTransaction';

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
                    <Route path="/withdraw" element={<Withdraw/>}/>
                    <Route path="/singleTransaction" element = {<SingleTransaction/>}/>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
