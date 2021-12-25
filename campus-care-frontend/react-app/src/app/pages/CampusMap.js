import map from "../../public/map.jpg";
import {Container, Row, Col} from "react-bootstrap";
import Form from "react-bootstrap/Form";
import MyButton from "../components/MyButton/MyButton";
import React, {useState} from "react";
import Card from "react-bootstrap/Button";
import MyModal from "../components/MyModal/MyModal";
import ReservationForm from "../components/appointmentComponents/ReservationForm";
const CampusMap = () => {
    const [modal, setModal] = useState(false);

    return (
        <Container >
            <Row>
                <img src = {map} alt = "404 not found" style={{position: 'center'}}/>
            </Row>
            <Row>
                <div className="col-2 align-center">
                    <Card
                        className="button"
                        style={{
                            backgroundColor: " #a3a9f5",
                            border: "none",
                            marginTop: 30,
                        }}
                        size="md"
                        onClick={() => setModal(true)}
                    >
                        New Appointment
                    </Card>
                </div>
            </Row>
            <Row>
                <Form>
                    <Form.Group>
                        <Form.Label className="col-12" style={{ marginLeft: "0px" }}>
                            Choose Type
                        </Form.Label>
                        <Form.Select>
                            <option>...</option>
                        </Form.Select>
                    </Form.Group>

                    <Row className="mb-2">
                        <Col>
                            <MyButton>
                                Reserve
                            </MyButton>
                        </Col>
                        <Col>
                            <MyButton>
                                Cancel
                            </MyButton>
                        </Col>
                    </Row>
                </Form>
            </Row>
            <MyModal
                style={{ position: "relative" }}
                visible={modal}
                setVisible={setModal}
            >
                Lmao
            </MyModal>
        </Container>
    )
};

export default CampusMap;