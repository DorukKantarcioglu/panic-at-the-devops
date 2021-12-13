import React from "react";
import InfoBox from "./InfoBox";
import {BrowserRouter as Router, Link, Route, Switch} from "react-router-dom";
import StudentList from "./StudentList";
import SideBar from "./SideBar";
import CoursePage from "./CoursePage";

class MyProfile extends React.Component {

    constructor() {
        super();
        this.state = {
            name: 'Elif',
            surname: 'Cenesiz',
            id: 21902461,
            hesCode: 'XXX'
        }
    }
render()
{

    return (

            <SideBar id = {this.state.id}  />

    );
}
}
export default MyProfile