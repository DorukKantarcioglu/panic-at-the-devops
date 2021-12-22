import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import InfoBox from "./InfoBox";
import React from "react";
import CoursePage from "../CoursePage";
import "./sideBarStyle.css" ;
import { Dropdown } from "react-bootstrap";


const SideBar = (props) => {
  let dropdown = document.getElementsByClassName("dropdown-btn");
  let i;

  for (i = 0; i < dropdown.length; i++) {
    dropdown[i].addEventListener("click", function() {
      this.classList.toggle("active");
      let dropdownContent = this.nextElementSibling;
      if (dropdownContent.style.display === "block") {
        dropdownContent.style.display = "none";
      } else {
        dropdownContent.style.display = "block";
      }
    });
  }
  return (
      <div className="sidenav">
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
          <button className="dropdown-btn">Dropdown
            <i className="fa fa-caret-down"></i>
          </button>
          <div className="dropdown-container">
            <Link to="/courses" className="side">
              Courses1
            </Link>
            <Link to="/courses" className="side">
              Courses1
            </Link>
            <Link to="/courses" className="side">
              Courses1
            </Link>
          </div>
        </li>
        <li className="side">
          <Link to="/appointments" className="side">
            Appointments
          </Link>
        </li>
      </ul>
      <Switch>
        <Route path="/profile">
          <InfoBox id={props.id} />
        </Route>
        <Route path="/profile-info">
          <InfoBox id={props.id} />
        </Route>

        <Route path="/courses">
          <CoursePage />
        </Route>
        <Route path="/appointments"></Route>
      </Switch>
    </Router>
        </div>


  );
};

export default SideBar;
