import React from "react";
import dateFormat from "dateformat";
import UserService from "../../api/UserService";
import "./UserList.css";
import UserEditModal from "../modal/UserEditModal";
import {Table, Container, Row, Col} from "react-bootstrap";

class UserListPage extends React.Component {
    constructor(props) {
        super(props)

        this.saveModalDetails = this.saveModalDetails.bind(this);
        this.getUserbyTcnoAndBirthdate = this.getUserbyTcnoAndBirthdate.bind(this);
        this.state = {
            items: [],
            isShow: false,
            requiredItem: 0,
            tcno: '',
            birthdate: '',
            item: {}
        }
        this.routeChange = this.routeChange.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
       this.getUserList();
    }

    handleModalShow = (id) => {
        this.setState({isShow: true});
        this.setState({requiredItem: id});
        console.log("ISSHOW = ", this.state.isShow);
    }

    handleModalClose = () => {
        this.setState({isShow: false});
    }

    handleResponse(response) {
        console.log("RESPONSE1 = ", response);
        this.setState({ items: response.data })
    }

    handleSearchResponse(response) {
        console.log("RESPONSE = ", response.data);
        if (response.data.tcno !== null) {
            const items = [response.data];
            this.setState({items: items});
          }
          else {
              alert("Kullanıcı bulunamadı!");
              this.getUserList();
          }
    }

    handleError(error) {
        console.log(error.data);
    }

    getUserList() {
        UserService.getAllUsers()
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error));
    }

    deleteData(user) {
        UserService.deleteUser(user.id)
            .then(response => this.getUserList())
            .catch(error => this.handleError(error));
    }

    remove(user) {
        this.deleteData(user);
    }

    saveModalDetails(updatedUser) {
        UserService.updateUser(updatedUser)
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error))
            ;
    }

    getUserbyTcnoAndBirthdate(tcno, birthdate) {
        console.log("TCKN = ", tcno);
        console.log("Birthdate = ", birthdate);
        UserService.getUserByTcNoAndBirthdate(tcno, birthdate)
        .then(response => this.handleSearchResponse(response))
        .catch(error => this.handleError(error));
        
    }

    handleChange = ( {target} ) => {
        console.log("target = ", target.value);
        this.setState({
          [target.name]: target.value
        });
    }

    setTcno(tcno1) {
        console.log(tcno1);
        this.setState({tcno: tcno1});
    }

    setBirthdate(birthdate1) {
        this.setState({birthdate: birthdate1});
    }
    
    routeChange = () =>{ 
        let path = `/user/add`; 
        this.props.history.push(path);
      }

    render() {
        const requiredItem = this.state.requiredItem;
        let modalData = this.state.items[requiredItem];
        console.log("modalData = ", modalData);
        console.log("items = ", this.state.items);

        let editModalClose = () => this.setState({isShow:false});

        return (
            <div className="row col-md-8 offset-md-2">
                <div className="title-container" style={{marginTop: "30px"}}>
                    <h1 style={{color: "darkslateblue"}}>Kredi Limit Tablosu</h1>
                </div>
                <Container style={{marginBottom: "20px", marginTop: "50px", marginLeft: "50px"}}>
                    <Row style={{display: "inline-flex"}}>
                        <Col width={60}>
                            <label>TCKN</label>
                            <input type="number" value={ this.state.tcno } onChange={(e) => this.setTcno(e.target.value)} required/>
                        </Col>
                        <Col width={60}>
                            <label>Doğum Tarihi</label>
                            <input type="date" placeholder="yyyy-mm-dd" value={ this.state.birthdate } 
                                onChange={(e) => this.setBirthdate(e.target.value)} required/>
                        </Col>
                        <Col style={{marginTop: "auto"}}>
                        <button className="btn btn-warning btn-xs" onClick={() =>this.getUserbyTcnoAndBirthdate(this.state.tcno, this.state.birthdate)} data-title="Search">
                            Ara
                        </button>
                        </Col>
                </Row>
                </Container>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>İsim</th>
                            <th>Soyisim</th>
                            <th>Telefon Numarası</th>
                            <th>Maaş</th>
                            <th>Doğum Tarihi</th>
                            <th>TCKN</th>
                            <th>Teminat Bedeli</th>
                            <th>Kredi Limiti</th>
                            <th>Kredi Sonucu</th>
                            <th>Güncelle</th>
                            <th>Sil</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.items.map((user, index) => {
                                return <tr key={index}>
                                    <td key="id">{user.id}</td>
                                    <td key="name">{user.name}</td>
                                    <td key="surname">{user.surname}</td>
                                    <td key="phoneNumber">{user.phoneNumber}</td>
                                    <td key="phoneNumber">{user.salary} TL</td>
                                    <td key="birthdate">{dateFormat(user.birthdate, "isoDate")}</td>
                                    <td key="tcno">{user.tcno}</td>
                                    <td key="guaranteeAmount">{user.guaranteeAmount} TL</td>
                                    <td key="creditLimit">{user.creditLimit} TL</td>
                                    <td key="creditResult" style={{fontWeight: "bold", color : user.creditResult === "ONAY" ? "green" : "red" }}>{user.creditResult}</td>
                                    <UserEditModal user = {this.state.items[requiredItem]} save={this.saveModalDetails} show={this.state.isShow} onHide={editModalClose}/>
                                    <td>
                                    <td>
                                        <button className="btn btn-primary btn-xs" onClick={() => this.handleModalShow(index)} 
                                        data-toggle="modal" data-target="#exampleModal" data-title="Edit">
                                            Güncelle
                                        </button>
                                    </td>
                                    </td>
                                    <td>
                                        <button className="btn btn-danger btn-xs" onClick={() =>this.remove(user)} data-title="Delete">
                                            Sil
                                        </button>
                                    </td>
                                </tr>
                            })
                        }
                    </tbody>
                    
                </Table>

                {/* <button className="btn btn-warning btn-xs" onClick={event =>  window.location.href='/user/add'} data-title="Add">
                    Add Credit Information
                </button> */}
            </div>
        )
    }


}

export default UserListPage;