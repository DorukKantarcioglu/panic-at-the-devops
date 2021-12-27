import {BrowserRouter as Router, Switch, Route, Link, Redirect} from "react-router-dom";
import InfoBox from "../profileComponents/InfoBox";
import React from "react";
import { Navigation } from "react-minimal-side-navigation";
import { FaUserAlt, FaBook } from "react-icons/fa";
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import "react-minimal-side-navigation/lib/ReactMinimalSideNavigation.css";
import CourseService from "../../../service/CourseService";
import InstructorService from "../../../service/InstructorService";
import LocalStorageService from "../../../service/LocalStorageService";
const SideBar = (props) => {

  const [courses, setCourses] = useState()
  const history = useHistory();
  let subNavArr = [];
  let list = [];


  useEffect(async () => {
    await fetchCourses()
  }, []);

  useEffect(async () => {
    if (courses)
    createSubNav()
  }, [courses]);

  const fetchCourses=async () => {
    list = await InstructorService.getCourses(LocalStorageService.getId());
    setCourses(list);
  }

  const createSubNav = () => {

    courses.forEach((c) => subNavArr.push({ itemId: c.courseCode, title: c.courseCode }));
    console.log(subNavArr)
  };

  return (
    <div>
      <>
        <div className="">
          <div className="col-3 justify-content-start">
            <ul className="side " style={{ margin: "1" }}>
              <Navigation
                // you can use your own router's api to get pathname
                activeItemId="/management/members"
                onSelect={({ itemId }) => {
                  if (itemId !== "/management" && itemId !== "/profile") {
                    let path;
                    console.log("history1 " + history.location.pathname)
                    console.log("substring " + history.location.pathname.substring(0,7))

                      path = "/course/".concat(itemId);
                      console.log("history2 " + history.location.pathname)
                      console.log("path: " + path)
                    history.replace(path)
                    }
                  else if (itemId === "/profile")
                  {
                    history.replace(itemId)
                  }

                }}
                items={[
                  {
                    title: "Profile Info",
                    itemId: "/profile",
                    // you can use your own custom Icon component as well
                    // icon is optional
                    elemBefore: () => <FaUserAlt />,
                  },
                  {
                    title: "Courses",
                    itemId: "/management",
                    elemBefore: () => <FaBook />,
                    subNav: subNavArr,
                  },
                ]}
              />
            </ul>
          </div>
        </div>
      </>
    </div>
  );
};

export default SideBar;
