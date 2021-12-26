import Form from "react-bootstrap/Form";
import {Col, Row} from "react-bootstrap";
import MyButton from "./MyButton/MyButton";
import React, {useState} from "react";
import ReservationService from "../../service/ReservationService";

const CampusMapSelection = ({create}) => {
    const [time, setTime] = useState({type: '', place: '', id: '' })

    const addSelection = (e) => {
        console.log("addSelection",e)
        e.preventDefault()
        console.log()
        const newTime = {
            ...time, id: Date.now()
        }
        console.log(newTime)
        create(newTime)
        setTime({place: '', timeSlot: '', id: ''})
    }

    return(
        <div>
            <Form>
                <Form.Group>
                    <Form.Label className="col-12" style={{ marginLeft: "0px" }}>
                        Choose Type
                    </Form.Label>
                    <Form.Select
                        as="select"
                        value={time.type}
                        onChange= { e => {
                            setTime({...time, type : e.target.value})}}
                        type= "text"
                    >
                        <option>...</option>
                        <option >Cafeteria</option>
                        <option >Smoking Area</option>
                    </Form.Select>
                </Form.Group>
                <Form.Group>
                    <Form.Label className="col-12" style={{ marginLeft: "0px" }}>
                        Choose Place
                    </Form.Label>
                    <Form.Select
                        as="select"
                        value={time.place}
                        onChange= { e => {
                            setTime({...time, place: e.target.value})}}
                        type= "text"
                    >
                        <option>...</option>
                        <option>Yemekhane/Cafeteria</option>
                        <option>Speed</option>
                        <option>Mozart</option>
                        <option>Smoking Area 1</option>
                        <option>Smoking Area 2</option>
                    </Form.Select>
                </Form.Group>
                <Row className="mb-2">
                    <Col>
                        <MyButton onClick = {addSelection}>
                            Select
                        </MyButton>
                    </Col>
                    <Col>
                        <MyButton>
                            Cancel
                        </MyButton>
                    </Col>
                </Row>
            </Form>
        </div>
    );
}
export default CampusMapSelection;
