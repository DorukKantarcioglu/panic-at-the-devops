import React, {useEffect, useState} from "react";
import Form from "react-bootstrap/Form";
import { Container, Col, Row } from "react-bootstrap";
import MyButton from "../MyButton/MyButton";
import ReservationService from "../../../service/ReservationService";

const ReservationForm = ({ create }) => {

  const [reservation, setReservation] = useState({type: '', place: '', timeSlot: '', date: ''})
  const [temp, setTemp] = useState({type: '', date: '', place: ''})
  const [options, setOptions] = useState({})

  const addAvailableTimes = (e) => {
    e.preventDefault()
    console.log(temp)

  }
  async function fetchAvailableTime(){
    const response = await ReservationService.getAvailableTimes(temp.type, temp.date, temp.place);
    console.log( " I was ", response)
    {response&& response.map(response0 =>
        setOptions([response0])
    )}
  }

  useEffect( async () => {
    await fetchAvailableTime();

  }, [])

  const addNewReservation = (e) => {
    console.log(e)
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
                Choose Type
              </Form.Label>
              <Form.Select
                  as="select"
                  value={reservation.type}
                  onChange= { e => {
                    setReservation({...reservation, type : e.target.value})
                    setTemp(({...temp, type: e.target.value}))}}
                  type= "text"
              >
                <option>...</option>
                <option>Diagnovir</option>
                <option>Library</option>
                <option>Sports Center</option>

              </Form.Select>
            </Form.Group>
            <Form.Group>
              <Form.Label className="col-12" style={{ marginLeft: "0px" }}>
                Choose Location
              </Form.Label>
              <Form.Select
                  as="select"
                  value={reservation.place}
                  onChange= { e => {
                    setReservation({...reservation, place: e.target.value})
                    setTemp({...temp, place: e.target.value})}}
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
                Date (dd.mm.yy)
              </Form.Label>
              <Form.Control
                  as="textarea"
                  value={reservation.date}
                  onChange= { e => {
                    setReservation({...reservation, date : e.target.value})
                    setTemp({...temp, date: e.target.value})}}
                  type= "date"
                  placeholder="Date"
              >

              </Form.Control>
            </Form.Group>

            <Row className="mb-2">
              <Col>
                <MyButton onClick = {addAvailableTimes} >
                       Get Available Times
                </MyButton>
              </Col>
            </Row>

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
