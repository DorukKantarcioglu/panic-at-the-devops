import './App.css';
import MyProfile from "./ProfilePage";
import ProfilePage from "./ProfilePage";
import ReservationCheck from "./reservationCheck";
import InfoBox from "./InfoBox";


import StudentInfoBox from "./StudentInfoBox";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {Layout, Menu, Breadcrumb} from "antd";
import MenuTab from "./MenuTab";
import StudentService from "./StudentService";
import {useEffect, useState} from "react";
import StudentList from "./StudentList";
import CoursePage from "./CoursePage";
import NewSeatingPlanForm from "./NewSeatingPlanForm";
const {Header, Content, Footer} = Layout;

function App() {


  return (

      <>
          <div>
              <Router>
                  <Header>
                      <div>
                          <MenuTab  />
                      </div>
                  </Header>
                  <Content className="site-layout"
                           style={{padding: "0 5 px", marginTop: 40}}>

                      <Switch>
                          <Route path="/home">
                              <CoursePage/>
                          </Route>
                          <Route path="/appointment">
                              <CoursePage/>
                          </Route>
                          <Route path="/profile">
                              <ProfilePage/>
                          </Route>
                          <Route path= "/campusmap">
                              <ReservationCheck/>
                          </Route>
                          <Route path= "/logout">
                              <InfoBox id = {21903224}/>
                          </Route>
                          <Route path="/addUser" >
                              <InfoBox id = {21903224}/>
                          </Route>
                      </Switch>

                  </Content>
              </Router>
          </div>
      </>
  );
}

export default App;
