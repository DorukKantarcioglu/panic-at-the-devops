import React, {useEffect, useState} from "react";
import ReservationForm from "../components/ReservationForm";
import MyModal from "../components/MyModal/MyModal";
import Card from "react-bootstrap/Button";
import ReservationsList from "../components/ReservationsList";
import ReservationService from "../../service/ReservationService";

const AppointmentPage = () => {
    const [modal, setModal] = useState(false);
    const[reservations, setReservations] = useState([
        {   date: "", timeSlot: "", place: "", type: "", userId: 0, id: 0, },
    ])

    const createReservation = (newReservation) => {
        setReservations([...reservations, newReservation])
    }

    async function fetchReservations(){
        const response = await ReservationService.getReservations();
        {response&& response.map(response0 =>
            setReservations([response0])
        )}
    }

    useEffect( async () => {
        await fetchReservations();
    }, [])
  return (
    <React.Fragment>
      <div className="row justify-content-start">
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
