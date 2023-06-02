import "./styles.css";

interface CardProps {
  price: number;
  title: string;
  image: string;
}

const Card = ({ price, image, title }: CardProps) => {
  return (
    <div className="card">
      <img src="" alt="" />
      <h2></h2>
      <p>
        <b>Valor:</b>
      </p>
    </div>
  );
};

export default Card;
