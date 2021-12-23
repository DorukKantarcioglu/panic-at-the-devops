import React, {useState} from "react";

import Card from "react-bootstrap/Button";
import { BrowserRouter as Router, Route, Switch, Link } from "react-router-dom";
import ReservationForm from "./components/ReservationForm";
import MyModal from "./components/MyModal/MyModal";
import Button from "react-bootstrap/Button";

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
      </div>
  );
};

export default AppointmentPage;
