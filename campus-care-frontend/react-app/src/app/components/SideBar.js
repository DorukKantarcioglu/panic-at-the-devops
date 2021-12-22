import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import InfoBox from "./InfoBox";
import React from "react";
import CoursePage from "../CoursePage";

const SideBar = (props) => {
  return (
    <Router>
      <ul className="side" style={{ padding: "10 px", marginTop: 5 }}>
        <li className="side">
          <Link to="/profile-info" className="side">
            Profile Info
          </Link>
        </li>
        <li className="side">
          <Link to="/courses" className="side">
            Courses
          </Link>
        </li>
        <li className="side">
          <Link to="/appointments" className="side">
            Appointments
          </Link>
        </li>
      </ul>

      <Switch>
        <Route path="/profile-info">
          <InfoBox id={props.id} />
        </Route>

        <Route path="/courses">
          <CoursePage />
        </Route>

        <Route path="/appointments"></Route>
      </Switch>
    </Router>
  );
};

export default SideBar;
