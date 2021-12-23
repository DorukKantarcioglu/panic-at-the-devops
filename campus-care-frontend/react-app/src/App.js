import "./App.css";
import ProfilePage from "./app/ProfilePage";
import InfoBox from "./app/components/profileComponents/InfoBox";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Layout } from "antd";
import "bootstrap/dist/css/bootstrap.min.css";
import "@fortawesome/react-fontawesome";
import "@fortawesome/free-solid-svg-icons";
import { IconName } from "react-icons/fa";
import MenuTab from "./app/components/MenuTab";
import CoursePage from "./app/CoursePage";
import Dashboard from "./app/Dashboard";
import Login from "./app/Login";
import Notifications from "./app/Notifications";
import AppointmentPage from "./app/AppointmentPage";
const { Header, Content } = Layout;

function App() {
  return (
    <>
      <div>
        <Router>
          <Header>
            <div>
              <MenuTab />
            </div>
          </Header>
          <Content>
            <Switch>
              <Route path="/home">
                <Dashboard />
              </Route>
              <Route path="/appointment">
                <AppointmentPage />
              </Route>
              <Route path="/profile">
                <ProfilePage id="1" />
              </Route>
              <Route path="/campusmap">
                <Login />
              </Route>
              <Route path="/logout">
                <InfoBox id={21903224} />
              </Route>
              <Route path="/notifications">
                <Notifications />
              </Route>
            </Switch>
          </Content>
        </Router>
      </div>
    </>
  );
}

export default App;
