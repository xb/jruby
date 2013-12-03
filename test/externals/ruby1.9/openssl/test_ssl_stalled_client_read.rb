require_relative "utils"

if defined?(OpenSSL)

class OpenSSL::TestSSLStalledClientRead < OpenSSL::SSLTestCase

  # testcase for https://github.com/jruby/jruby/issues/1280
  def test_read_from_server_after_stalling
    port = 12345
    start_server(port, OpenSSL::SSL::VERIFY_NONE, true, :server_proc =>
      Proc.new do |server_ctx, server_ssl|
        server_ssl.io.write("\x01") # write random data which may be part of a TLS record
        sleep 5                     # do not finish prematurely before the read by the client is attempted
      end
    ) do |server, port|
      sock = TCPSocket.new("127.0.0.1", port)
      ssl = OpenSSL::SSL::SSLSocket.new(sock)
      ssl.connect
      sleep 3           # wait is required for the (incomplete) TLS record to arrive at the client socket
      begin
        a = ssl.read(1) # with the bug, spins here forever, consuming 100% CPU load, because the TLS record is only available incompletely
      rescue => e
        # without the bug, execution should reach this place (due to a malformed TLS record, now that it is clear that the stream is closed)
      end
    end
  end
end

end
