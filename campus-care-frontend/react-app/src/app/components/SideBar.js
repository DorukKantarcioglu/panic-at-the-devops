import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import InfoBox from "./profileComponents/InfoBox";
import React from "react";
import CoursePage from "../CoursePage";
import { Navigation } from "react-minimal-side-navigation";
import { FaUserAlt, FaBook } from "react-icons/fa";
import "react-minimal-side-navigation/lib/ReactMinimalSideNavigation.css";
const SideBar = (props) => {
  return (
    <>
      <div className="">
        <div className="col-3 justify-content-start">
          <ul className="side " style={{ margin: "1" }}>
            <Navigation
              // you can use your own router's api to get pathname
              activeItemId="/management/members"
              onSelect={({ itemId }) => {
                // maybe push to the route
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
                  subNav: [
                    {
                      title: "Projects",
                      itemId: "/management/projects",
                    },
                    {
                      title: "Members",
                      itemId: "/management/members",
                    },
                  ],
                },
                {
                  title: "Another Item",
                  itemId: "/another",
                  subNav: [
                    {
                      title: "Teams",
                      itemId: "/management/teams",
                    },
                  ],
                },
              ]}
            />
          </ul>
        </div>
      </div>
    </>
  );
};

export default SideBar;
