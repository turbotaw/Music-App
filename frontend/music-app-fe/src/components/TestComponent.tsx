import { useUser } from "./UserContext";

const TestComponent = () => {
    const userContextValue = useUser();
    return (
        <div>
            <span data-testid="userId">{userContextValue.userId}</span>
        </div>
    );
};

export default TestComponent;
