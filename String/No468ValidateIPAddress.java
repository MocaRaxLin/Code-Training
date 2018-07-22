package String;

public class No468ValidateIPAddress {

	public static void main(String[] args) {
		No468ValidateIPAddress sol = new No468ValidateIPAddress();
		
		String[] inputs = new String[]{
				"172.16.254.1",
				"256.256.256.256",
				"00.00.00.00",
				"0.0.0.0",
				"155.255.255.255",
				"100",
				"001:0db8:85a3:0:0:8A2E:0370:7334"
		};
		for(String ip: inputs) {
			String ans = sol.validIPAddress(ip);
			System.out.println(ans);
		}
	}
	
	public String validIPAddress(String IP) {
		// --> O(1), because IP is standard format with fixed length
		// i.e. ipv4.length() < 16, ipv6.length() < 40
		// Also the length of regular expression of ipv4 and ipv6 is invariant
		// Therefore we validate IP in constant time.
		
        String ipv4 = "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}"
            +"([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
        String ipv6 = "(([0-9a-fA-F]{1,4})\\:){7}([0-9a-fA-F]{1,4})";
        if(IP.matches(ipv4)) return "IPv4";
        else if(IP.matches(ipv6)) return "IPv6";
        else return "Neither";
    }
	
}
