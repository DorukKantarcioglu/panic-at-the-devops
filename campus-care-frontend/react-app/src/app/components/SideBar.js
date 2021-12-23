import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import InfoBox from "./profileComponents/InfoBox";
import React from "react";
import CoursePage from "../CoursePage";
import { Navigation } from "react-minimal-side-navigation";
import { FaUserAlt, FaBook } from "react-icons/fa";
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import "react-minimal-side-navigation/lib/ReactMinimalSideNavigation.css";

const courses = [
  {
    name: "cs319",
    itemId: "1",
  },
  {
    name: "cs315",
    itemId: "2",
  },
  {
    name: "math225",
    itemId: "3",
  },
];

const SideBar = (props) => {
  const [path, setPath] = useState("");
  const history = useHistory();
  let subNavArr = [];
  useEffect(() => {
    createSubNav();
  }, []);

  const createSubNav = () => {
    courses.forEach((c) => subNavArr.push({ title: c.name, itemId: c.itemId }));
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
                onSelect={({ itemId, title }) => {
                  if (itemId !== "/management") {
                    setPath("course/".concat(itemId));
                    history.push({ path });
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
      <Router>
        <Switch>
          <Route path={path}>
            <CoursePage />
          </Route>
        </Switch>
      </Router>
    </div>
  );
};

export default SideBar;
