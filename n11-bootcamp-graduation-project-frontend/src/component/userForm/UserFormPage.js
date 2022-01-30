import React from "react";
import 'react-phone-number-input/style.css'
import serialize from 'form-serialize';
import moment from 'moment'
import InputMask from 'react-input-mask';
import {Row, Col} from "react-bootstrap";
import UserService from "../../api/UserService";
import "./UserFormPage.css";

class UserFormPage extends React.Component {
    state = {
        user: {},
        value: 0,
        birthdateValue: 0,
        birthDate: moment()
            .locale('en')
            .format('YYYY-MM-DD')
    };

    setPhoneValue = (phoneNumber) => {
        this.setState({value: phoneNumber});
    }

    setBirthdateValue = (birthdate) => {
        this.setState({birthdateValue: birthdate});
    }

    handleFormSubmit = (e) => {
        e.preventDefault();
        const newUser = serialize(e.target, { hash: true })
        this.save(newUser);
    }

    save(newUser) {
        UserService.saveUserAndCreditDetail(newUser)
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error));
            this.clearForm();
    }

    handleResponse(response) {
        alert("Kullanıcı başarıyla kaydedildi!");
    }

    handleError(error) {
        console.log(error.data);
        alert("An error was occured when save user!");
    }

    clearForm() {
        document.getElementById("user-form").reset();
    }

    render() {
        return(
            <div className="container col-md-6 offset-md-3">
                <div className="title">
                    <h1 style={{marginTop: "30px", color: "darkslateblue"}}>Kullanıcı Ekle</h1>
                </div>
                <form id="user-form" className="mt-5" onSubmit={this.handleFormSubmit}>
                    <Row className="row-size">
                        <Col className="col order-first">
                            <div className="form-group">
                                <label htmlFor="inputName">Adı</label>
                                <input type="text"
                                    className="form-control"
                                    required
                                    name="name" />
                            </div>
                        </Col>
                        <Col className="col">
                            <div className="form-group">
                                <label htmlFor="inputName">Soyadı</label>
                                <input type="text"
                                    className="form-control"
                                    required
                                    name="surname" />
                            </div>
                        </Col>
                        <Col className="col order-last">
                            <div className="form-group ">
                                <label htmlFor="inputImage">Maaş</label>
                                <input
                                    type="text"
                                    required
                                    className="form-control"
                                    name="salary" />
                            </div>
                        </Col>
                        </Row>
                        <Row className="row-size">
                            <Col className="col order-first">
                                <div className="phone-number">
                                    <label>Telefon Numarası</label>
                                    <InputMask 
                                        style={{height: "36px"}}
                                        mask='+\909999999999'
                                        required
                                        name="phoneNumber"
                                        value={this.state.value}
                                        onChange={() => this.setPhoneValue()}
                                    />
                                </div>
                            </Col>
                            <Col className="col">
                                <div className="form-group " >
                                    <label htmlFor="inputRating">Doğum Tarihi</label>
                                    <input
                                        type="date"
                                        className="form-control"
                                        required
                                        placeholder="yyyy-mm-dd"
                                        name="birthdate" />
                                </div>
                            </Col>
                            <Col className="col order-last">
                                <div className="form-group ">
                                    <label htmlFor="inputRating">Teminat Bedeli</label>
                                    <input
                                        className="form-control"
                                        name="guaranteeAmount" />
                                </div>
                            </Col>
                        
                        </Row>
                        <Row>
                            <Col xs={6} md={4}>
                                <div className="form-group ">
                                    <label htmlFor="inputRating">TC Kimlik Numarası</label>
                                    <input
                                        className="form-control"
                                        required
                                        name="TCNo" />
                                </div>
                            </Col>
                            
                        </Row>
                        <Row>
                        <Col className="col order-last">
                                <input type="submit" className="btn btn-danger btn-block" value="Kaydet" style={{marginTop: "30px"}}/>
                            </Col>
                        </Row>                          

                </form>
            </div>
        )
    }
}

export default UserFormPage;