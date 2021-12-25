import { Route, Switch, Router } from "react-router-dom";
import Dashboard from "../../pages/Dashboard";
import AppointmentPage from "../../pages/AppointmentPage";
import ProfilePage from "../../pages/ProfilePage";
import Login from "../../pages/Login";
import InfoBox from "../profileComponents/InfoBox";
import Notifications from "../../pages/Notifications";
import CoursePage from "../../pages/CoursePage";
import { Redirect } from "react-router-dom";
import NewSeatingPlanForm from "../profileComponents/NewSeatingPlanForm";
import MenuTab from "../MenuTab";
import CourseService from "../../../service/CourseService";
import { useContext, useEffect, useState } from "react";
import courseContext from "../../CourseContext";
import SideBar from "../SideBar/SideBar";
import CampusMap from "../../pages/CampusMap";

const AppRouter = () => {

  const [courses, setCourses] = useState([]);



  const fetchData = async () => {
    let list = await CourseService.getAllCourses();
      setCourses(list)
      console.log(list)

  };

  useEffect( async () => {
      await fetchData();
      console.log(courses)
  }, []);

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
        <MenuTab />
          <CampusMap/>
        <CoursePage courseCode="MATH-230-1" />
      </Route>
      <Route path="/notifications">
        <MenuTab />
        <Notifications />
      </Route>

      {courses && courses.map((course) => {
        return (
          <Route path={"/course/".concat(course.courseCode)}>
            <MenuTab />
              <SideBar/>
            <CoursePage courseCode ={course.courseCode}/>
          </Route>
        );
      })}
      <Redirect to="/login" />
    </Switch>
  );
};
export default AppRouter;
