import { Route, Switch, Router } from "react-router-dom";
import Dashboard from "../pages/Dashboard";
import AppointmentPage from "../pages/AppointmentPage";
import ProfilePage from "../pages/ProfilePage";
import Login from "../pages/Login";
import InfoBox from "./profileComponents/InfoBox";
import Notifications from "../pages/Notifications";
import CoursePage from "../pages/CoursePage";
import { Redirect } from "react-router-dom";
import NewSeatingPlanForm from "./profileComponents/NewSeatingPlanForm";
import MenuTab from "./MenuTab";

const AppRouter = () => {
  return (
    <Switch>
      <Route path="/login">
        <Login />
      </Route>
      <Route path="/home">
        <MenuTab />
        <Dashboard />
      </Route>
      <Route path="/appointment">
        <MenuTab />
        <AppointmentPage />
      </Route>
      <Route path="/profile">
        <MenuTab />
        <ProfilePage id="1" />
      </Route>
      <Route path="/campusmap">
        <CoursePage courseCode = "MATH-230-1"/>
        <Login />
      </Route>
      <Route path="/notifications">
        <MenuTab />
        <Notifications />
      </Route>
      <Route path="/course/cs315">
        <MenuTab />
        <CoursePage />
      </Route>
      <Redirect to="/login" />
    </Switch>
  );
};
export default AppRouter;
