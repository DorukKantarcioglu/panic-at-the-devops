import React, {useState} from "react";
import Form from "react-bootstrap/Form";
import { Container, Col, Row } from "react-bootstrap";
import MyButton from "../MyButton/MyButton";

const ReservationForm = ({ create }) => {

  const [reservation, setReservation] = useState({place: '', timeSlot: ''})
  const addNewReservation = (e) => {
    e.preventDefault()
    console.log(reservation)
    const newReservation = {
      ...reservation, id: Date.now()
    }
    create(newReservation)
    setReservation({place: '', timeSlot: ''})

  }

  return (
    <Container>
      <Row>
        <Col sm={12}>
          <Form>
            <Form.Group>
              <Form.Label className="col-12" style={{ marginLeft: "0px" }}>
                Choose location
              </Form.Label>
              <Form.Select
                  as="select"
                  value={reservation.place}
                  onChange= { e => {
                    setReservation({...reservation, place: e.target.value})}}
                  type= "text"
              >
                <option>Main Sports Hall</option>
                <option>Dormitory Sports Hall</option>
                <option>Library ( Main Campus )</option>
                <option>Library ( East Campus )</option>
                <option>Diagnovir</option>
              </Form.Select>
            </Form.Group>

            <Form.Group>
              <Form.Label className="col-12" style={{ marginLeft: "0px" }}>
                Time Interval
              </Form.Label>
              <Form.Select
                  as="select"
                  value={reservation.timeSlot}
                  onChange= { e => {
                    setReservation({...reservation, timeSlot : e.target.value})}}
                  type= "text"
              >
                <option>...</option>

              </Form.Select>
            </Form.Group>

            <Row className="mb-2">
              <Col>
                <MyButton onClick = {addNewReservation} >
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
        </Col>
      </Row>
    </Container>
  );
};

export default ReservationForm;
