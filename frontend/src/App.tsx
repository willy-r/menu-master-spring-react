import { useState } from "react";

import Card from "./components/Card";
import Modal from "./components/Modal";
import { useFoodData } from "./hooks/useFoodData";

import "./assets/styles/App.css";

const App = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const { data } = useFoodData();

  const handleModal = () => {
    setIsModalOpen((prev) => !prev);
  };

  return (
    <div className="container">
      <h1>
        Menu<span>Master</span>
      </h1>
      <div className="card-grid">
        {data?.map((foodData) => (
          <Card
            key={foodData.id}
            price={foodData.price}
            title={foodData.title}
            image={foodData.image}
          />
        ))}
      </div>
      <button onClick={handleModal}>New food</button>
      {isModalOpen && <Modal />}
    </div>
  );
};

export default App;
