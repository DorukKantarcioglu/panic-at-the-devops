import React, {useEffect, useState} from "react";
import { BrowserRouter as Router, Link, Route, Switch } from "react-router-dom";
import InfoBox from "./components/InfoBox";
import SideBar from "./components/SideBar";
import StudentService from "../service/StudentService";
import InstructorService from "../service/InstructorService";

function MyProfile(props){

    return (
      <React.Fragment>
        <div className="row"> </div>
        <div col-4>
          {" "}
          <SideBar id={props.id} />
        </div>
      </React.Fragment>
    );
  }

export default MyProfile;
