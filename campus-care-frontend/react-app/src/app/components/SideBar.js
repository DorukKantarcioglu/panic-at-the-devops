import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import InfoBox from "./profileComponents/InfoBox";
import React from "react";
import { Navigation } from "react-minimal-side-navigation";
import { FaUserAlt, FaBook } from "react-icons/fa";
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import "react-minimal-side-navigation/lib/ReactMinimalSideNavigation.css";
import CourseService from "../../service/CourseService";

const courses = [
  {
    name: "cs319",
  },
  {
    name: "cs315",
  },
  {
    name: "math225",
  },
];

const SideBar = (props) => {
  const history = useHistory();
  let subNavArr = [];
  useEffect(() => {
    createSubNav();
  }, []);

  const createSubNav = () => {
    courses.forEach((c) => subNavArr.push({ title: c.name, itemId: c.name }));
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
                  if (itemId !== "/management") {
                    let path = "course/".concat(itemId);
                    history.push(path);
                  }
                }}
                items={[
                  {
                    title: "Profile Info",
                    itemId: "/dashboard",
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
