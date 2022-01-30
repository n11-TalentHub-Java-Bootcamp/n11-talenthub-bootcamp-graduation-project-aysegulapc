import React from "react";
import {Modal, Button, Container, Row, Col} from "react-bootstrap";
import InputMask from 'react-input-mask';
import UserService from "../../api/UserService";
import {Form} from "react-bootstrap";
import serialize from 'form-serialize';
import "./UserEditModal.css";

class UserEditModal extends React.Component {
    constructor(props) {
        super(props)

        this.handleSubmit = this.handleSubmit.bind(this);

        this.state = {
            items: [],
            isShow: true,
            requiredItem: 0,
            name: '',
            surname: '',
            phoneNumber: '',
            birthdate: '',
            guaranteeAmount: 0,
            salary: 0,
            tcno: 0
        }
    }

    componentDidMount() {
        UserService.getAllUsers()
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error));
    }

    handleModalShow = () => {
        this.setState({isShow: true});
    }

    handleModalClose = () => {
        this.setState({isShow: false});
    }

    userNameHandler(e) {
        this.setState({ name: e.target.value });
    }

    userSurnameHandler(e) {
        this.setState({ surname: e.target.value });
    }

    userPhoneNumberHandler(e) {
        this.setState({ phoneNumber: e.target.value });
    }

    userBirthdateHandler(e) {
        this.setState({ birthdate: e.target.value });
    }

    userTCNoHandler(e) {
        this.setState({ tcno: e.target.value });
    }

    userGuaranteeAmountHandler(e) {
        this.setState({ guaranteeAmount: e.target.value });
    }

    userSalaryHandler(e) {
        this.setState({ salary: e.target.value });
    }

    handleSubmit = (e) => {
        e.preventDefault();
        const newUser = serialize(e.target, { hash: true })
        this.update(this.props.user.id, newUser);
    }

    update(id, user) {
        console.log(user);
        UserService.updateUser(id, user)
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error));
        alert("Update successfully");
    }

    render() {
        return (
            <Modal {...this.props} size="lg"  centered id="exampleModal" onHide={this.props.onHide}
                aria-labelledby="example-modal-sizes-title-lg">
                    <Modal.Header closeButton>
                        <Modal.Title>Kullanıcıyı Düzenle</Modal.Title>
                    </Modal.Header>
                    <Modal.Body className="show-grid">
                    <Form onSubmit={this.handleSubmit}>
                        <Container>
                        <Row className="row-size">
                            <Col className="col order-first">
                                <Form.Group controlId="name">
                                    <Form.Label>Ad</Form.Label>
                                    <Form.Control 
                                        type="text"
                                        name="name"
                                        required
                                        defaultValue={this.props.user.name}
                                        placeholder="User Name"
                                    />    
                                </Form.Group>
                            </Col>
                            <Col className="col">
                            <Form.Group controlId="surname">
                                    <Form.Label>Soyad</Form.Label>
                                    <Form.Control 
                                        type="text"
                                        name="surname"
                                        required
                                        defaultValue={this.props.user.surname}
                                        placeholder="User Surname"
                                    />    
                                </Form.Group>
                            </Col>
                            <Col className="col order-last">
                            <Form.Group controlId="salary">
                                    <Form.Label>Maaş</Form.Label>
                                    <Form.Control 
                                        type="number"
                                        name="salary"
                                        required
                                        defaultValue={this.props.user.salary}
                                        placeholder="Salary"
                                    />    
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row className="row-size">
                            <Col className="col order-first phone-number">
                                <label>Telefon Numarası</label>
                                <InputMask 
                                    style={{height: "36px", marginTop: "5px"}}
                                    defaultValue={this.props.user.phoneNumber}
                                    onChange={(e) => this.userPhoneNumberHandler(e)}
                                    type="text"
                                    required
                                    mask='+\909999999999'
                                    name="phoneNumber"
                                /> 
                           
                            </Col>
                            <Col className="col">
                                <Form.Group controlId="birthdate">
                                    <Form.Label>Doğum Tarihi</Form.Label>
                                    <Form.Control 
                                        type="date"
                                        name="birthdate"
                                        required
                                        defaultValue={this.props.user.birthdate}
                                        placeholder="Birthdate"
                                    />    
                                </Form.Group>
                            </Col>
                            <Col className="col order-last">
                                <Form.Group controlId="guaranteeAmount">
                                    <Form.Label>Teminat Bedeli</Form.Label>
                                    <Form.Control 
                                        type="number"
                                        name="guaranteeAmount"
                                        required
                                        defaultValue={this.props.user.guaranteeAmount}
                                        placeholder="Guarantee Amount"
                                    />    
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row className="row-size">
                            <Col className="col order-first">
                                <Form.Group controlId="guaranteeAmount">
                                    <Form.Label>TCKN</Form.Label>
                                    <Form.Control 
                                        maxLength="11"
                                        type="number"
                                        name="tcno"
                                        required
                                        defaultValue={this.props.user.tcno}
                                        placeholder="TCKN"
                                    />   
                                </Form.Group> 
                            </Col>
                            <Col className="col">
                                <Button style={{marginTop: "32px"}} variant="primary" type="submit">
                                    Değişiklikleri Kaydet
                                </Button>
                            </Col>
                            <Col className="col order-last"></Col>
                        </Row>
                        </Container>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.props.onHide}>
                            İptal
                        </Button>
                    </Modal.Footer>
                </Modal>
        )
    }
}

export default UserEditModal;