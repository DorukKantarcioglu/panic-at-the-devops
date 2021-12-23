import React from "react";
import ReservationCheckForm from "./components/ReservationCheckForm";
import Card from "react-bootstrap/Button";
import { BrowserRouter as Router, Route, Switch, Link } from "react-router-dom";
import ReservationForm from "./components/ReservationForm";

const AppointmentPage = () => {
  return (
    <Router>
      <ReservationCheckForm></ReservationCheckForm>
      <Link to="/new">
        <div className="row justify-content-center">
          <div className="col-6">
            <Card
              className=" button"
              style={{ backgroundColor: " #a3a9f5", border: "none" }}
              size="md"
            >
              {" "}
              New Appointment
            </Card>
          </div>
          <div className="col-6"></div>
        </div>
      </Link>

      <Switch>
        <Route path="/new">
          <ReservationForm />
        </Route>
      </Switch>
    </Router>
  );
};

export default AppointmentPage;