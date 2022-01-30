import React from "react";
import { Navbar, Container, Nav } from 'react-bootstrap/';

class Menu extends React.Component {

    constructor(props){
        super(props)

        this.handleLogout = this.handleLogout.bind(this);
    }

    handleLogout(){
        this.props.logout();
    }

    render() {
        return <div className="col-md-8 offset-md-2 ">
            <Navbar bg="light" expand="lg">
                <Container>
                    <Navbar.Brand href="/">
                        <img src='https://n11scdn.akamaized.net/a1/org/21/04/01/67/61/13/35/59/54/78/68/68/62194584164326158720.svg' width={100} height={64} />
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="/">Ana Sayfa</Nav.Link>
                            <Nav.Link href="/user/add">Kullanıcı Ekle</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </div>


    }
}

export default Menu;