import { Route, Switch } from "react-router-dom";
import Dashboard from "../pages/Dashboard";
import AppointmentPage from "../pages/AppointmentPage";
import ProfilePage from "../pages/ProfilePage";
import Login from "../pages/Login";
import InfoBox from "./profileComponents/InfoBox";
import Notifications from "../pages/Notifications";
import CoursePage from "../pages/CoursePage";
import { Redirect } from "react-router-dom";
import NewSeatingPlanForm from "./profileComponents/NewSeatingPlanForm";

const AppRouter = () => {
  return (
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
        <NewSeatingPlanForm />
      </Route>
      <Route path="/notifications">
        <Notifications />
      </Route>
      <Route path="/course/cs315">
        <CoursePage />
      </Route>
      <Redirect to="/home" />
    </Switch>
  );
};
export default AppRouter;
