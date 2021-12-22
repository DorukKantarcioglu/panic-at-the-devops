import "./App.css";
import ProfilePage from "./app/ProfilePage";
import ReservationCheck from "./app/components/reservationCheckForm";
import InfoBox from "./app/components/InfoBox";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Layout } from "antd";
import "bootstrap/dist/css/bootstrap.min.css";
import "@fortawesome/react-fontawesome";
import "@fortawesome/free-solid-svg-icons";
import MenuTab from "./app/components/MenuTab";
import CoursePage from "./app/CoursePage";
import Dashboard from "./app/Dashboard";
import Notifications from "./app/Notifications";
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
                <ReservationCheck />
              </Route>
              <Route path="/profile">
                <ProfilePage />
              </Route>
              <Route path="/campusmap">
                <CoursePage />
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
