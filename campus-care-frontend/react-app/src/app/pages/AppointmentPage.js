import React, {useEffect, useState} from "react";
import ReservationForm from "../components/appointmentComponents/ReservationForm";
import MyModal from "../components/MyModal/MyModal";
import Card from "react-bootstrap/Button";
import ReservationsList from "../components/appointmentComponents/ReservationsList";
import ReservationService from "../../service/ReservationService";
import InstructorService from "../../service/InstructorService";
import LocalStorageService from "../../service/LocalStorageService";
import StudentService from "../../service/StudentService";

const AppointmentPage = (props) => {
    const [modal, setModal] = useState(false);
    const[reservations, setReservations] = useState([
        {   date: "", timeSlot: "", place: "", type: "", userId: 0, id: 0, },
    ])

    const createReservation = (newReservation) => {
        setReservations([...reservations, newReservation])
    }

    async function fetchReservations(){

        try{
            let response = await  InstructorService.getReservations(LocalStorageService.getId())
            setReservations(response)
        } catch (e){
            let response = await StudentService.getReservations(LocalStorageService.getId())
            setReservations(response)
        }
    }

    useEffect( async () => {
        await fetchReservations();

    }, [])
  return (
    <React.Fragment>
      <div className="row justify-content-start">
        <div className="col-2 align-center" >
          <Card
            className="button"
            style={{
              backgroundColor: " #a3a9f5",
              border: "none",
              marginTop: 30,
            }}
            size="md"
            onClick={() => setModal(true)}
            hidden={props.hidden}

          >
            New Appointment
          </Card>
        </div>

        <div className="col-7 " style={{ alignContent: "center" }}>
          <ReservationsList reservations={reservations}/>
        </div>
      </div>
      <MyModal
        style={{ position: "relative" }}
        visible={modal}
        setVisible={setModal}
      >
        <ReservationForm create={createReservation}/>
      </MyModal>
    </React.Fragment>
  );
};

export default AppointmentPage;
