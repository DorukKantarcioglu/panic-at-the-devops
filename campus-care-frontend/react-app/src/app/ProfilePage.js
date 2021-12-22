import React from "react";
import { BrowserRouter as Router, Link, Route, Switch } from "react-router-dom";
import InfoBox from "./components/InfoBox";
import SideBar from "./components/SideBar";
import StudentList from "./components/StudentList";

class MyProfile extends React.Component {
  constructor() {
    super();
    this.state = {
      name: "Elif",
      surname: "Cenesiz",
      id: 21902461,
      hesCode: "XXX",
    };
  }

  render() {
    return (
      <React.Fragment>
        <div className="row"> </div>
        <div col-4>
          {" "}
          <SideBar id={this.state.id} />
        </div>
        <div>
          <InfoBox />
          <StudentList />
        </div>
      </React.Fragment>
    );
  }
}
export default MyProfile;
