import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import InfoBox from "./profileComponents/InfoBox";
import React from "react";
import CoursePage from "../CoursePage";
import { Dropdown } from "react-bootstrap";

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
          <Dropdown>
            <Dropdown.Toggle variant="success" id="dropdown-basic">
              Dropdown Button
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="#/action-1">Action</Dropdown.Item>
              <Dropdown.Item href="#/action-2">Another action</Dropdown.Item>
              <Dropdown.Item href="#/action-3">Something else</Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
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
