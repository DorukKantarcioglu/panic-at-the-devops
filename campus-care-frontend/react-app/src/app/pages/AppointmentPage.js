import React, {useEffect, useState} from "react";
import ReservationForm from "../components/ReservationForm";
import MyModal from "../components/MyModal/MyModal";
import Card from "react-bootstrap/Button";
import ReservationsList from "../components/ReservationsList";
import ReservationService from "../../service/ReservationService";

const AppointmentPage = () => {
    const [modal, setModal] = useState(false);



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
          <ReservationsList/>
        </div>
      </div>
      <MyModal
        style={{ position: "relative" }}
        visible={modal}
        setVisible={setModal}
      >
        <ReservationForm/>
      </MyModal>
    </React.Fragment>
  );
};

export default AppointmentPage;
