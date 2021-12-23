import React from "react";
import * as ReactDOM from "react-dom";
import NotificationList from "./components/NotificationList";
import StudentList from "./components/profileComponents/StudentList";
import { Dropdown } from "react-bootstrap";
import LoginForm from "./components/loginComponents/LoginForm";
import NewSeatingPlanForm from "./components/profileComponents/NewSeatingPlanForm";

function Notifications(props) {
  return (
    <React.Fragment>
      <div className="row justify-content-start">
        <h1 className="display-6 m-2" style={{ color: "grey" }}>
          Notifications
        </h1>
        <div className="col-6 m-0">
          <NotificationList />
        </div>
        <div className="col-6 m-0">
          {" "}
        </div>
      </div>
    </React.Fragment>
  );
}
export default Notifications;
