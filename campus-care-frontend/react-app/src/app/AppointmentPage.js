import React from "react";
import ReservationCheck from "./components/reservationCheckForm";
import Button from 'react-bootstrap/Button'
import { BrowserRouter as Router, Route, Switch, Link} from "react-router-dom";
import ReservationForm from "./components/ReservationForm";

const AppointmentPage = () => {
    return(
            <Router>
                <Link to="/new">
                    <Button variant="dark" size ="lg" >New Appointment</Button>
                </Link>
                <ReservationCheck />

                <Switch>
                    <Route path = "/new">
                        <ReservationForm/>
                    </Route>
                </Switch>
            </Router>

    );
}

export default AppointmentPage;