import React, {useState} from "react";
import ReservationForm from "../components/ReservationForm";
import MyModal from "../components/MyModal/MyModal";
import Button from "react-bootstrap/Button";
import ReservationsList from "../components/ReservationsList";

const AppointmentPage = () => {
    const [modal, setModal] = useState(false);
  return (
      <div className="App">
          <Button style={{marginTop: 30}} onClick={() => setModal(true)}>
              New Appointment
          </Button>
          <MyModal visible={modal} setVisible={setModal}>
              <ReservationForm/>
          </MyModal>
          <br/>
          <div>
              <ReservationsList/>
          </div>
      </div>
  );
};

export default AppointmentPage;
