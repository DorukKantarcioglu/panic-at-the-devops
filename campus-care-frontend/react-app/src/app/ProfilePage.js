import React, {useEffect, useState} from "react";
import { BrowserRouter as Router, Link, Route, Switch } from "react-router-dom";
import InfoBox from "./components/InfoBox";
import SideBar from "./components/SideBar";

import StudentService from "../service/StudentService";
import InstructorService from "../service/InstructorService";
import StudentList from "./components/StudentList";
function MyProfile(props){

    return (
      <React.Fragment>
        <div className="row"> </div>
        <div col-4>
          {" "}
          <SideBar id={props.id} />
        </div>
        <div>
          <InfoBox />
          <StudentList />
        </div>
      </React.Fragment>
    );
  }

export default MyProfile;
