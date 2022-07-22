import logo from '../assets/image/Logo.webp'
import '../styles/Navbar.css'

const Navbar =() => {
    return (
        <>
            <nav className="navbar navbar-expand-sm navbar-light bg-light">
                <div className="container">
                    <a href="#" className="navbar-brand"><img src={logo} alt="Logo"/></a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item"><a href="/" className="nav-link">Home</a></li>
                            <li className="nav-item dropdown">
                                <a className="nav-link dropdown-toggle" href="" id="navbarDropdown-1" role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    Transaction
                                </a>
                                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a className="dropdown-item" href="/SingleTransaction">Single Transaction</a></li>
                                    <li><a className="dropdown-item" href="/BatchTransaction">Batch Transaction</a></li>
                                    <li><a className="dropdown-item" href="#">Something else</a></li>
                                </ul>
                            </li>
                            <li className="nav-item dropdown">
                                <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown-2" role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    Quick Transaction
                                </a>
                                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a className="dropdown-item" href="/Balance">Balance</a></li>
                                    <li><a className="dropdown-item" href="/Purchase">Purchase</a></li>
                                    <li><a className="dropdown-item" href="/Withdraw">Withdraw</a></li>
                                    <li><a className="dropdown-item" href="/Transfer">Transfer</a></li>
                                    <li><a className="dropdown-item" href="/ChangePIN">Change PIN</a></li>
                                </ul>
                            </li>
                            <li className="nav-item"><a href="" className="nav-link">ISO-8583</a></li>
                            <li className="nav-item"><a href="" className="nav-link">EN</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </>
    )

}


export default Navbar