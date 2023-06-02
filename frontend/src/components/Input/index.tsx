import "./styles.css";

interface InputProps {
  label: string;
  value: string | number;
  updateValue(value: any): void;
}

const Input = ({ label, value, updateValue }: InputProps) => {
  return (
    <>
      <label className="main-label">{label}</label>
      <input
        className="main-input"
        value={value}
        onChange={(event) => updateValue(event.target.value)}
      />
    </>
  );
};

export default Input;
