import './styles/App.css';
import Purchase from './pages/purchase/Purchase';
import Result from './components/Result';
import Balance from './pages/balance/Balance';
import Home from './pages/home/home'

import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'

function App() {
    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/purchase" element={<Purchase/>}/>
                    <Route path="/result" element={<Result/>}/>
                    <Route path="/balance" element={<Balance/>}/>
                </Routes>
            </Router>

        </div>
    );
}

export default App;
