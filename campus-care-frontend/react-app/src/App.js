import "./App.css";

import { BrowserRouter, Route } from "react-router-dom";

import { Layout } from "antd";
import "bootstrap/dist/css/bootstrap.min.css";
import "@fortawesome/react-fontawesome";
import "@fortawesome/free-solid-svg-icons";
import MenuTab from "./app/components/MenuTab";

import AppRouter from "./app/components/Navigation/AppRouter";
import Login from "./app/pages/Login";
import {useEffect} from "react";
const { Header, Content } = Layout;

function App() {


  return (

    <BrowserRouter>
      <Content>
        <AppRouter />
      </Content>
    </BrowserRouter>
  );
}

export default App;
