import './App.css';
import ProfilePage from "./app/ProfilePage";
import ReservationCheck from "./app/components/reservationCheckForm";
import InfoBox from "./app/components/InfoBox";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
//import {Layout} from "antd";
import MenuTab from "./app/components/MenuTab";
import CoursePage from "./app/CoursePage";
const {Header, Content} = Layout;

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
