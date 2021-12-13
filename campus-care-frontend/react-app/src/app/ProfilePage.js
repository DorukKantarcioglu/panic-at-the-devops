import React from "react";
import {BrowserRouter as Router, Link, Route, Switch} from "react-router-dom";
import SideBar from "./components/SideBar";

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