import React from "react";
import * as ReactDOM from "react-dom";
import NotificationList from "./components/NotificationList";

function Notifications(props) {
  return (
    <React.Fragment>
      <div className="row justify-content-center">
        <NotificationList />
      </div>
    </React.Fragment>
  );
}
export default Notifications;
