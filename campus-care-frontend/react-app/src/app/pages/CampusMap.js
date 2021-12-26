import map from "../../public/map.jpg";
import {Container, Row, Col} from "react-bootstrap";
import Form from "react-bootstrap/Form";
import MyButton from "../components/MyButton/MyButton";
import React, {useEffect, useState} from "react";
import Card from "react-bootstrap/Button";
import MyModal from "../components/MyModal/MyModal";
import ReservationForm from "../components/appointmentComponents/ReservationForm";
import CampusMapSelection from "../components/CampusMapSelection";
import ReservationService from "../../service/ReservationService";
import CampusMapList from "../components/CampusMapList";

const CampusMap = () => {
    const [modal, setModal] = useState(false);
    const[campusmap, setCampusMap] = useState([
        { type: "",  place: "", },
    ])

    const createCampusMap = (newCampusMap) => {
        setCampusMap([...campusmap, newCampusMap])
    }

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
                <CampusMapList campusmap={campusmap}/>
            </Row>
            <MyModal
                style={{ position: "relative" }}
                visible={modal}
                setVisible={setModal}
            >
                <CampusMapSelection create = {createCampusMap}/>
            </MyModal>
        </Container>
    )
};

export default CampusMap;