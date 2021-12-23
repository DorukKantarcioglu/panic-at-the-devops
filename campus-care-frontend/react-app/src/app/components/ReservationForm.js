import React from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import {Container, Col, Row} from 'react-bootstrap'

function ReservationForm() {
    return (
        <Container>
            <Row>
                <Col sm ={4}>
                    <h5> Appointment System</h5>
                    <Button>
                        Sports Center
                    </Button>
                    <br/>
                    <Button>
                        Library
                    </Button>
                </Col>
                <Col sm = {8}>
                    <Form>
                        <Form.Group as={Col} >
                            <Form.Label>Sports Hall</Form.Label>
                            <Form.Select defaultValue="Choose..." >
                                <option>Main Sports Hall</option>
                                <option>Dormitory Sports Hall</option>
                            </Form.Select>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formGridAddress2">
                            <Form.Label>Identification</Form.Label>
                            <Form.Control placeholder="Please Enter Your ID" />
                        </Form.Group>

                        <Form.Group>
                                <Form.Label>Time Interval</Form.Label>
                                <Form.Select defaultValue="Choose...">
                                    <option>10:30</option>
                                    <option>...</option>
                                </Form.Select>
                        </Form.Group>

                        <Row className="mb-2">
                            <Col>
                                <Button variant="primary" type="submit">
                                    Reserve
                                </Button>
                            </Col>
                            <Col>
                                <Button variant="primary" type="submit">
                                    Cancel
                                </Button>
                            </Col>
                        </Row>
                        <Button variant="primary" type="submit">
                            Submit
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default ReservationForm;