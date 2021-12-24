import "./App.css";

import { BrowserRouter} from "react-router-dom";

import { Layout } from "antd";
import "bootstrap/dist/css/bootstrap.min.css";
import "@fortawesome/react-fontawesome";
import "@fortawesome/free-solid-svg-icons";
import MenuTab from "./app/components/MenuTab";

import AppRouter from "./app/components/AppRouter";
const { Header, Content } = Layout;

function App() {
  return (
    <BrowserRouter>
        <Header>
          <div>
            <MenuTab />
          </div>
        </Header>
        <Content>
          <AppRouter/>
        </Content>
    </BrowserRouter>


  );
}

export default App;
